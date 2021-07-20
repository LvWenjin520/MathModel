package datamanager.json;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import Jama.Matrix;
import model.logisticregression.beans.FlowerBean;

/**
 * json处理类
 * @author LvWenJin
 *
 */
public class JsonDataManager {
	
	/**
	 * 获取训练集数据
	 * @param fileName 文件位置
	 * @return 特征矩阵
	 * @throws IOException 
	 */
	public static Map<String,Matrix> getTrainingDataSet(String fileName) throws IOException {
		Map<String,Matrix> result = new HashMap<>();
		File readFile=new File(fileName);
        //输入IO流声明
       InputStream in=null;
       InputStreamReader ir=null;
       BufferedReader br=null;
       StringBuffer str = new StringBuffer(); 
       try {
           //用流读取文件
           in=new BufferedInputStream(new FileInputStream(readFile));
           //如果你文件已utf-8编码的就按这个编码来读取，不然又中文会读取到乱码
           ir=new InputStreamReader(in,"utf-8");
           //字符输入流中读取文本,这样可以一行一行读取
           br= new BufferedReader(ir);
           String temp = "";
           //一行一行读取
           while((temp = br.readLine())!=null){
        	   str.append(temp);
           }
           
           JsonParser json = new JsonParser();
           JsonElement parse = json.parse(str.toString());
           JsonArray array = parse.getAsJsonArray();
           
           Gson gson = new Gson();
           
           //特征矩阵
           double[][] feature = new double[array.size()][5];
           double[][] flag = new double[array.size()][1];
           FlowerBean fromJson;
           for(int i=0;i<array.size();i++) {
        	   feature[i][0] = 1;
        	   fromJson = gson.fromJson(array.get(i), FlowerBean.class);
        	   feature[i][1] = fromJson.getSepalLength();
        	   feature[i][2] = fromJson.getSepalWidth();
        	   feature[i][3] = fromJson.getPetalLength();
        	   feature[i][4] = fromJson.getPetalWidth();
        	   if(fromJson.getSpecies().equals("setosa")) {
        		   flag[i][0] = 0;
        	   }else {
        		   flag[i][0] = 1;
        	   }
           }
           result.put("feature",new Matrix(feature));
           result.put("flag",new Matrix(flag));
           
       } catch (Exception e) {
           e.printStackTrace();
       }finally{
           //一定要关闭流,倒序关闭
           try {
               if(br!=null){
                   br.close();
               }
               if(ir!=null){
                   ir.close();
               }
               if(in!=null){
                   in.close();
               }
           } catch (Exception e2) {
                
           }
       }
		return result;
	}
	
	
}
