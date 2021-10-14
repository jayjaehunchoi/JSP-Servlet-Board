package domain.file;

public class MyFile {
	
	private Long id;
	private Long bbsId;
	private String fileName;
	private String fileRealName;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBbsId() {
		return bbsId;
	}
	public void setBbsId(Long bbsId) {
		this.bbsId = bbsId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileRealName() {
		return fileRealName;
	}
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
	public MyFile(Long bbsId, String fileName, String fileRealName) {
		this.bbsId = bbsId;
		this.fileName = fileName;
		this.fileRealName = fileRealName;
	}
	public MyFile() {

	}
	
	
}
