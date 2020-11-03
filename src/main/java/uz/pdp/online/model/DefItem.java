package uz.pdp.online.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DefItem{

	@SerializedName("pos")
	private String pos;

	@SerializedName("text")
	private String text;

	@SerializedName("tr")
	private List<TrItem> tr;

	public void setPos(String pos){
		this.pos = pos;
	}

	public String getPos(){
		return pos;
	}

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
			"DefItem{" + 
			"pos = '" + pos + '\'' + 
			",text = '" + text + '\'' + 
			",tr = '" + tr + '\'' + 
			"}";
		}
}