package kr.or.ddit.vo;

import java.io.Serializable;

public class MemberVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userid;
	private String usernm;
	private String pass;
	private String reg_dt;
	private String alias;
	private String addr1;
	private String addr2;
	private String zipcode;
	private String filename;
	private String realfilename;
	
	private MemberVO() {
		super();
	}

	private MemberVO(String userid, String usernm, String pass, String reg_dt, String alias, String addr1, String addr2,
			String zipcode, String filename, String realfilename) {
		super();
		this.userid = userid;
		this.usernm = usernm;
		this.pass = pass;
		this.reg_dt = reg_dt;
		this.alias = alias;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipcode = zipcode;
		this.filename = filename;
		this.realfilename = realfilename;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsernm() {
		return usernm;
	}

	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getRealfilename() {
		return realfilename;
	}

	public void setRealfilename(String realfilename) {
		this.realfilename = realfilename;
	}

	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", usernm=" + usernm + ", pass=" + pass + ", reg_dt=" + reg_dt
				+ ", alias=" + alias + ", addr1=" + addr1 + ", addr2=" + addr2 + ", zipcode=" + zipcode + ", filename="
				+ filename + ", realfilename=" + realfilename + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addr1 == null) ? 0 : addr1.hashCode());
		result = prime * result + ((addr2 == null) ? 0 : addr2.hashCode());
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((realfilename == null) ? 0 : realfilename.hashCode());
		result = prime * result + ((reg_dt == null) ? 0 : reg_dt.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		result = prime * result + ((usernm == null) ? 0 : usernm.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (addr1 == null) {
			if (other.addr1 != null)
				return false;
		} else if (!addr1.equals(other.addr1))
			return false;
		if (addr2 == null) {
			if (other.addr2 != null)
				return false;
		} else if (!addr2.equals(other.addr2))
			return false;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (realfilename == null) {
			if (other.realfilename != null)
				return false;
		} else if (!realfilename.equals(other.realfilename))
			return false;
		if (reg_dt == null) {
			if (other.reg_dt != null)
				return false;
		} else if (!reg_dt.equals(other.reg_dt))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		if (usernm == null) {
			if (other.usernm != null)
				return false;
		} else if (!usernm.equals(other.usernm))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}
	
	public static class MemberVOBuilder {
		private String userid;
		private String usernm;
		private String pass;
		private String reg_dt;
		private String alias;
		private String addr1;
		private String addr2;
		private String zipcode;
		private String filename;
		private String realfilename;
		
		public MemberVOBuilder userid(String userid) {
			this.userid = userid;
			return this;
		}
		
		public MemberVOBuilder usernm(String usernm) {
			this.usernm = usernm;
			return this;
		}
		
		public MemberVOBuilder pass(String pass) {
			this.pass = pass;
			return this;
		}
		
		public MemberVOBuilder reg_dt(String reg_dt) {
			this.reg_dt = reg_dt;
			return this;
		}
		
		public MemberVOBuilder alias(String alias) {
			this.alias = alias;
			return this;
		}
		
		public MemberVOBuilder addr1(String addr1) {
			this.addr1 = addr1;
			return this;
		}
		
		public MemberVOBuilder addr2(String addr2) {
			this.addr2 = addr2;
			return this;
		}
		
		public MemberVOBuilder zipcode(String zipcode) {
			this.zipcode = zipcode;
			return this;
		}
		
		public MemberVOBuilder filename(String filename) {
			this.filename = filename;
			return this;
		}
		
		public MemberVOBuilder realfilename(String realfilename) {
			this.realfilename = realfilename;
			return this;
		}
		
		public MemberVO build() {
			return new MemberVO(userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode, filename, realfilename);
		}
	}
	
	public static MemberVOBuilder getBuilder() {
		return new MemberVOBuilder();
	}
}