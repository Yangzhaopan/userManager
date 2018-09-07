package cn.pzy.domain;
//解决中文乱码问题
public class myTools{
	public static String getNewString(String str) {
		String newString="";
		try {
			newString=new String(str.getBytes("iso-8859-1"),"utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			// 把iso-8859-1 转换成 utf-8
		} 
		return newString;
	}

}
