package com.topwulian.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 基地
 */
public class Farm extends BaseEntity<Long> {

	private String province;
	private String city;
	private String district;
	private String type;
	private String name;
	private String linkman;
	private String telephone;
	private String remark;
	private String createTimeStr;
    private Long userId;

    //主要农作物
    private String mainCrop;

    //面积
    private Double area;

    private String img;


    private String longitude;
    private String latitude;

    private String plantstd;

	public String getPlantstd() {
		return plantstd;
	}

	public void setPlantstd(String plantstd) {
		this.plantstd = plantstd;
	}

	private List<FarmEntQua> listFarmEntQua;

	public List<FarmEntQua> getListFarmEntQua() {
		return listFarmEntQua;
	}

	public void setListFarmEntQua(List<FarmEntQua> listFarmEntQua) {
		this.listFarmEntQua = listFarmEntQua;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

    public String getCreateTimeStr() {
        return createTimeStr;
    }
    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

	public String getMainCrop() {
		return mainCrop;
	}

	public void setMainCrop(String mainCrop) {
		this.mainCrop = mainCrop;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}
