package site.metacoding.red.util;

public class Script {
	// 재활요하기 위해서 스크립트를 따로 뺌
	// static, 메모리에 일단 메서드의 이름이 뜸
	// new 안 하고 호출할 수 있도록
	public static String back(String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back();");
		sb.append("</script>");
		return sb.toString();
	}
	public static String href(String url) {

		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("location.href='"+url+"';");
		sb.append("</script>");
		return sb.toString();
		
	}
	public static String href(String url, String msg) {

		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("location.href='"+url+"';");
		sb.append("</script>");
		return sb.toString();
	}
}
