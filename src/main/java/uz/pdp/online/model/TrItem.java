package uz.pdp.online.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TrItem{

	@SerializedName("ex")
	private List<ExItem> ex;

	@SerializedName("pos")
	private String pos;

	@SerializedName("mean")
	private List<MeanItem> mean;

	@SerializedName("syn")
	private List<SynItem> syn;

	@SerializedName("text")
	private String text;

	public void setEx(List<ExItem> ex){
		this.ex = ex;
	}

	public List<ExItem> getEx(){
		return ex;
	}

	public void setPos(String pos){
		this.pos = pos;
	}

	public String getPos(){
		return pos;
	}

	public void setMean(List<MeanItem> mean){
		this.mean = mean;
	}

	public List<MeanItem> getMean(){
		return mean;
	}

	public void setSyn(List<SynItem> syn){
		this.syn = syn;
	}

	public List<SynItem> getSyn(){
		return syn;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"TrItem{" + 
			"ex = '" + ex + '\'' + 
			",pos = '" + pos + '\'' + 
			",mean = '" + mean + '\'' + 
			",syn = '" + syn + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}