package utility;

import java.net.URI;

import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import restrequest.RequestObj;
import restrequest.RequestParams;

public class Utils {
	private static RestTemplate restTemplate;

	/**
	 * create Header of Request
	 *
	 * @param accessToken
	 *            token of login's session
	 * @param deviceId
	 *            device id of login's session
	 * @return
	 */
	public static HttpHeaders createRestHeader(String accessToken, String deviceId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		if (accessToken != null) {
			headers.set(HeaderConstants.FREQUENCY_AUTH_HEADER, accessToken);
		}
		if (deviceId != null) {
			headers.set(HeaderConstants.FREQUENCY_DEVICE_ID_HEADER, deviceId);
		}
		return headers;
	}

	/**
	 * Create basic URI
	 *
	 * @param apiURL
	 *            URL of API
	 * @return URI
	 */
	public static URI createURI(String apiURL) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiURL);

		return uriBuilder.build().toUri();
	}

	/**
	 * Create full URL with query parameters
	 *
	 * @param strUri
	 *            URL of API
	 * @param reqObject
	 *            request object
	 * @return full URI
	 */
	public static URI createPathParams(String strUri, RequestObj reqObject) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(strUri);

		if (StringUtils.isNotEmpty(reqObject.getPageMark())) {
			builder.queryParam(RequestParams.PAGE_MARK, reqObject.getPageMark());
		}
		if (StringUtils.isNotEmpty(reqObject.getPageOffer())) {
			builder.queryParam(RequestParams.PAGE_OFFER, reqObject.getPageOffer());
		}
		if (reqObject.getPageSize() > 0) {
			builder.queryParam(RequestParams.PAGE_SIZE, reqObject.getPageSize());
		}
		if (StringUtils.isNotEmpty(reqObject.getSize())) {
			builder.queryParam(RequestParams.SIZE, reqObject.getSize());
		}
		if (StringUtils.isNotEmpty(reqObject.getType())) {
			builder.queryParam(RequestParams.TYPE, reqObject.getType());
		}
		if (StringUtils.isNotEmpty(reqObject.getStartTimeStamp())) {
			builder.queryParam(RequestParams.START_TIMESTAMP, reqObject.getStartTimeStamp());
		}
		if (StringUtils.isNotEmpty(reqObject.getEndTimeStamp())) {
			builder.queryParam(RequestParams.END_TIMESTAMP, reqObject.getEndTimeStamp());
		}
		if (StringUtils.isNotEmpty(reqObject.getSort())) {
			builder.queryParam(RequestParams.SORT, reqObject.getSort());
		}
		if (StringUtils.isNotEmpty(reqObject.getTitle())) {
			builder.queryParam(RequestParams.TITLE, reqObject.getTitle());
		}
		if (StringUtils.isNotEmpty(reqObject.getStatus())) {
			builder.queryParam(RequestParams.STATUS, reqObject.getStatus());
		}
		if (StringUtils.isNotEmpty(reqObject.getImageSize())) {
			builder.queryParam(RequestParams.IMAGE_SIZE, reqObject.getImageSize());
		}
		if (StringUtils.isNotEmpty(reqObject.getPageOffset())) {
			builder.queryParam(RequestParams.PAGE_OFFSET, reqObject.getPageOffset());
		}
		if (reqObject.getSourceID() > 0) {
			builder.queryParam(RequestParams.SOURCE_ID, reqObject.getSourceID());
		}
		if (StringUtils.isNotEmpty(reqObject.getFormat())) {
			builder.queryParam(RequestParams.FORMAT, reqObject.getFormat());
		}
		if (StringUtils.isNotEmpty(reqObject.getSourceFieldName())) {
			builder.queryParam(RequestParams.SOURCE_FIELD_NAME, reqObject.getSourceFieldName());
		}
		if (StringUtils.isNotEmpty(reqObject.getSourceValue())) {
			builder.queryParam(RequestParams.SOURCE_VALUE, reqObject.getSourceValue());
		}
		if (StringUtils.isNotEmpty(reqObject.getMappedFieldName())) {
			builder.queryParam(RequestParams.MAPPED_FIELD_NAME, reqObject.getMappedFieldName());
		}
		if (StringUtils.isNotEmpty(reqObject.getIsMapped())) {
			builder.queryParam(RequestParams.IS_MAPPED, reqObject.getIsMapped());
		}
		if (StringUtils.isNotEmpty(reqObject.getGenre())) {
			builder.queryParam(RequestParams.GENRE, reqObject.getGenre());
		}
		if (StringUtils.isNotEmpty(reqObject.getImageType())) {
			builder.queryParam(RequestParams.IMAGE_TYPE, reqObject.getImageType());
		}
		if (StringUtils.isNotEmpty(reqObject.getChannelType())) {
			builder.queryParam(RequestParams.CHANNEL_TYPE, reqObject.getChannelType());
		}
		if (StringUtils.isNotEmpty(reqObject.getAssetType())) {
			builder.queryParam(RequestParams.ASSET_TYPE, reqObject.getAssetType());
		}
		return builder.build().encode().toUri();
	}

	public static RestTemplate getRestTemplate() {
		if (restTemplate == null) {
			restTemplate = new RestTemplate(clientHttpRequestFactory());
		}
		return restTemplate;
	}

	private static ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create()
						// .setProxy(new HttpHost("fsoft-proxy.fsoft.fpt.vn",
						// 8080, "http"))
						.build());

		factory.setReadTimeout(10000);
		factory.setConnectTimeout(10000);
		return factory;
	}
}