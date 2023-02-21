package yorramn.dev.v1.enums;

public enum Regions {
	SUDESTE("SUDESTE"), CENTRO_OESTE("CENTRO_OESTE"), NORDESTE("NORDESTE"), SUL("SUL"), NORTE("NORTE");
	private String region;

	private Regions(String region) {
		this.region = region;
	}

	public String getRegion() {
		return region;
	}
}