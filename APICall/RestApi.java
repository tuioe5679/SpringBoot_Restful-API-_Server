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
			myConnet.setRequestProperty("Content-Type", "application/json"); // header Content-Type ����
			myConnet.setRequestProperty("auth", "myAuth"); // header�� auth ����
			myConnet.setDoOutput(true); // �����κ��� �޴� ���� �ִٸ� true
			
			// �����κ��� ������ �о����

			BufferedReader br = new BufferedReader(new InputStreamReader(myConnet.getInputStream(),"UTF-8"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = br.readLine()) != null) { // ���� �� ���� �� ���� �ݺ�
				sb.append(line.replace("[", "").replace("]", ""));
			}
			JSONObject obj = new JSONObject(sb.toString()); // json���� ���� (������ȭ)
			System.out.println("id= " + obj.getString("id") + " / name= " + obj.getString("name")+
			                   "phone= " + obj.getString("phone") + " / name= " + obj.getString("address"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
