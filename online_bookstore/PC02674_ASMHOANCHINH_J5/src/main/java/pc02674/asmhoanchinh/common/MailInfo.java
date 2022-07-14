package pc02674.asmhoanchinh.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailInfo {
	String form;
	String to;
	String[] cc;
	String[] bcc;
	String body;
	String subject;
	String[] attachments;
	
	public MailInfo(String to, String subject, String body) {
		this.form ="Nhà sách KGB  <KGBbookstore@fpt.edu.vn>";
		this.to = to;
		this.subject =subject;
		this.body = body;
	}
	
}
