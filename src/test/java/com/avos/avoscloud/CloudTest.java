package com.avos.avoscloud;

import java.util.Arrays;
import java.util.Map;

import junit.framework.TestCase;

public class CloudTest extends TestCase {

  @Override
  public void setUp() {
    TestApp.init();
//    AVCloud.setProductionMode(false);
  }

  public void testRPCHollo() throws Exception {
    AVCloud.rpcFunction("hello", null);
    Map<String, Object> map = AVCloud.rpcFunction("complexObject", null);
    assertEquals("ComplexObject", ((AVObject) map.get("avObject")).getClassName());
    assertNotSame(0, map.size());
  }

  public void testRPCBareAVObject() throws Exception {
    AVObject result = AVCloud.rpcFunction("bareAVObject", null);
    assertEquals("ComplexObject", result.getClassName());
  }

  public void testRPCComplexObject() throws Exception {
    AVObject complexObject = new AVObject("ComplexObject");
    complexObject.put("name", "avObject");
    String content = "hahahahh12j9fhjdsahahahahh12j9fhjdsa\njsoidfjiosadfjo\n";
    AVFile testFile = new AVFile("hello.txt", content.getBytes());
    complexObject.put("avFile", testFile);
    complexObject.save();
    AVCloud.rpcFunction("testBareAVObjectParams", complexObject);
    AVCloud.rpcFunction("testAVObjectsArrayParams", Arrays.asList(complexObject));
    // no exception
  }
}
