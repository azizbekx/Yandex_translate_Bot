package uz.pdp.online.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseModel implements Serializable {

	@SerializedName("head")
	private Head head;

	@SerializedName("def")
	private List<DefItem> def;

	public void setHead(Head head){
		this.head = head;
	}

	public Head getHead(){
		return head;
	}

	public void setDef(List<DefItem> def){
		this.def = def;
	}

	public List<DefItem> getDef(){
		return def;
	}

	@Override
 	public String toString(){
		return 
			"ResponseModel{" +
			"head = '" + head + '\'' + 
			",def = '" + def + '\'' + 
			"}";
		}
}