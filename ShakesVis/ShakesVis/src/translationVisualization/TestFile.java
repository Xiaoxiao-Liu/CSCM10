/**
 * @Title:TestFile.java 
 * @Description:TODO
 * @Copyright:Copyright(c) 2018. All rights reserved.
 * @Company:Swansea University
 *
 * @author Xiaoxiao Liu
 * @date 2018年3月25日
 * @version 1.0
 */
package translationVisualization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Title:TestFile 
 * @Description:TODO
 * @Company:Swansea Univerisity
 *
 * @author Your Name
 * @data 2018年3月25日
 */
public class TestFile {
	
	
	public boolean printReader() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println(br.readLine());
         InputStreamReader stReader = new InputStreamReader(System.in);
		
//        String str =  stRea
		System.out.println(stReader);
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		TestFile tf=new TestFile();
		
		tf.printReader();
	}
	
	
	

}
