package com.jzs.pojo;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class Dom4jTest {
  @Test
  public void test1() throws Exception{
    //创建一个SAXReader输入流，去读取xml配置文件，声称document对象
    SAXReader saxReader=new SAXReader();
    try {
      Document doc = saxReader.read("src/books.xml");
      System.out.println(doc);
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
  /*
  读取books.xml声称book类
   */
  @Test
  public void test2() throws Exception{
    //1.读取books.xml文件
    SAXReader reader=new SAXReader();
    //在Junit测试中，相对路径是从模块名开始算
    Document document=reader.read("src/books.xml");
    //2.通过document对象获取根元素
    Element rootElement=document.getRootElement();
    //System.out.println(rootElement);
    //3.通过根元素获取book标签对象
    //element()和elements()都是通过标签名查子元素
    List<Element> books=rootElement.elements("book");
    //4.遍历，处理每个book对象转换为Book类
    for(Element book:books){
      //asXML()把标签对象转化为字符串
      Element nameElement=book.element("name");
      //System.out.println(nameElement.asXML());
      //getText();可以获取标签中的文本内容
      String nameText=nameElement.getText();
      //System.out.println(nameText);
      //直接获取指定标签名的文本内容
      String priceText=book.elementText("price");
      //System.out.println(priceText);
      String authorText=book.elementText("author");

      String snValue=book.attributeValue("sn");

      System.out.println(new Book(snValue,nameText,new BigDecimal(priceText),authorText));

    }
  }

}
