package swing2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class RestApi {

	public static void main(String[] args)  {
		try {
			URL SpringApi = new URL("http://localhost:5000/user/all");
			
			HttpURLConnection myConnet = (HttpURLConnection) SpringApi.openConnection();
			
			myConnet.setRequestMethod("GET");
			myConnet.setRequestProperty("Content-Type", "application/json"); // header Content-Type 정보
			myConnet.setRequestProperty("auth", "myAuth"); // header의 auth 정보
			myConnet.setDoOutput(true); // 서버로부터 받는 값이 있다면 true
			
			// 서버로부터 데이터 읽어오기

			BufferedReader br = new BufferedReader(new InputStreamReader(myConnet.getInputStream(),"UTF-8"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = br.readLine()) != null) { // 읽을 수 있을 때 까지 반복
				sb.append(line.replace("[", "").replace("]", ""));
			}
			JSONObject obj = new JSONObject(sb.toString()); // json으로 변경 (역직렬화)
			System.out.println("id= " + obj.getString("id") + " / name= " + obj.getString("name")+
			                   "phone= " + obj.getString("phone") + " / name= " + obj.getString("address"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
