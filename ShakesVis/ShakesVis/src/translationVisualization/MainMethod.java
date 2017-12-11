package translationVisualization;

public class MainMethod {
	private static double m_scaleValue=100;
	/** the width of the ScrollPanel */
	private final static int SCROLL_PANEL_WIDTH=420;
	
	/** the height of the ScrollPanel */
	private final static int SCROLL_PANEL_HEIGHT=330;
	
	public static void main(String[] args) throws Exception{
		
		long startTime=System.currentTimeMillis();   //获取开始时间  
		
		
		TranslationVisualization transVis=new TranslationVisualization();
		transVis.initiateTransVis();
		transVis.initiateFrame();
//		doSomeThing();  //测试的代码段  
//		long startTime=System.currentTimeMillis();   //获取开始时间  
		
		long endTime=System.currentTimeMillis(); //获取结束时间  
		System.out.println("Time： "+(endTime-startTime)+"ms");  
	}

}
