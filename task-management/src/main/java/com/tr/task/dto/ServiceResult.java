package com.tr.task.dto;

import java.util.List;

import com.tr.task.enums.ServiceResultType;

public class ServiceResult<T> {

	private ServiceResultType resultType;

	private T data;

	private List<ErrorDetail> errors;

	public ServiceResult(List<ErrorDetail> errors) {
		this(ServiceResultType.ERROR, errors);
	}

	public ServiceResult(T data) {
		this(ServiceResultType.SUCCESS, data);
	}

	public ServiceResult(ServiceResultType resultType, List<ErrorDetail> errors) {
		super();
		this.resultType = resultType;
		this.errors = errors;
	}

	public ServiceResult(ServiceResultType resultType, T data) {
		super();
		this.resultType = resultType;
		this.data = data;
	}

	public ServiceResult(ServiceResultType resultType, T data, List<ErrorDetail> errors) {
		super();
		this.resultType = resultType;
		this.data = data;
		this.errors = errors;
	}

	public ServiceResultType getResultType() {
		return resultType;
	}

	public void setResultType(ServiceResultType resultType) {
		this.resultType = resultType;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<ErrorDetail> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDetail> errors) {
		this.errors = errors;
	}

}
