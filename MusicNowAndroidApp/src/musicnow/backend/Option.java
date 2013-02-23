package musicnow.backend;

public class Option {

	private Integer index;
	private String name;

	public Option(Integer index, String name) {
		this.index = index;
		this.name = name;
	}

	public Integer getIndex() {
		return this.index;
	}

	public String getDescription() {
		return this.name;
	}
}