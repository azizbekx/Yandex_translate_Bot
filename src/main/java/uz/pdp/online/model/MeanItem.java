package uz.pdp.online.model;

import com.google.gson.annotations.SerializedName;

public class MeanItem{

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
			"MeanItem{" + 
			"text = '" + text + '\'' + 
			"}";
		}
}