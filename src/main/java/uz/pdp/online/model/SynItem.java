package uz.pdp.online.model;

import com.google.gson.annotations.SerializedName;

public class SynItem{

	@SerializedName("text")
	private String text;

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"SynItem{" + 
			"text = '" + text + '\'' + 
			"}";
		}
}