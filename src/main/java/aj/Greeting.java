package aj;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Greeting {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private long id;
	
	@NotNull
	@Size(min=2,max=30)
	private String content;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
