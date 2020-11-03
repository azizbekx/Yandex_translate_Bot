package uz.pdp.online.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ExItem{

	@SerializedName("text")
	private String text;

	@SerializedName("tr")
	private List<TrItem> tr;

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setTr(List<TrItem> tr){
		this.tr = tr;
	}

	public List<TrItem> getTr(){
		return tr;
	}

	@Override
 	public String toString(){
		return 
			"ExItem{" + 
			"text = '" + text + '\'' + 
			",tr = '" + tr + '\'' + 
			"}";
		}
}