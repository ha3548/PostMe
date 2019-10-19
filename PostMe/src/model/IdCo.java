package model;

public class IdCo { //id�옉 comment �떞�뒗 �겢�옒�뒪
   private String id;
   private String ment;

   public IdCo(String id, String ment) {
      this.id = id;
      this.ment = ment;
   }

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getMent() {
	return ment;
}

public void setMent(String ment) {
	this.ment = ment;
}
   
}