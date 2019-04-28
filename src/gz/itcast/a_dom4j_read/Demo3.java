package gz.itcast.a_dom4j_read;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * 练习-完整读取xml文档内容
 */
public class Demo3 {

    @Test
    public void test() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File("./src/contact.xml"));

        //读取根标签
        Element rootElem = doc.getRootElement();

        StringBuffer sb = new StringBuffer();
        getChildNodes(rootElem, sb);

        System.out.println(sb.toString());
    }

    /**
     * 获取当前标签的子标签
     */
    private void getChildNodes(Element elem, StringBuffer sb) {
//        System.out.println(elem.getName());
        //开始标签
        sb.append("<" + elem.getName());

        //得到标签的属性列表
        List<Attribute> attrs = elem.attributes();
        if (attrs != null) {
            for (Attribute attr : attrs) {
//            System.out.println(attr.getName()+"="+attr.getValue());
                sb.append(" " + attr.getName() + "=\"" + attr.getValue() + "\"");
            }
        }
        sb.append(">");

        //得到本文
//        String content = elem.getText();
//        System.out.println(content);

        Iterator<Node> it =  elem.nodeIterator();
        while(it.hasNext()){
           Node node = it.next();
           //标签
            if(node instanceof Element){
                Element el = (Element) node;
                getChildNodes(el,sb);
            }
            //文本
            if(node instanceof Text){
                Text text = (Text) node;
                sb.append(text.getText());
            }
        }
        sb.append("</"+elem.getName()+">");
    }
}
