package za.co.sabs.planningtool.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class HttpUtil {
    public static <T, D> T post(D d, RestTemplate restTemplate, String url, Class<T> responseClass) {
        final HttpEntity<D> httpEntity = getHttpEntity(d);
        final HttpEntity<T> responseEntity = restTemplate.exchange(url,
            HttpMethod.POST, httpEntity, responseClass);
        return responseEntity.getBody();
    }

    public static <T, D> T post(D d, RestTemplate restTemplate, String url, ParameterizedTypeReference<T> responseClass) {
        final HttpEntity<D> httpEntity = getHttpEntity(d);
        final HttpEntity<T> responseEntity = restTemplate.exchange(url,
            HttpMethod.POST, httpEntity, responseClass);
        return responseEntity.getBody();
    }


    public static <T, D> T post(D d, String token, RestTemplate restTemplate, String url, Class<T> responseClass) {
        final HttpEntity<D> httpEntity = getHttpEntity(d, token);
        final HttpEntity<T> responseEntity = restTemplate.exchange(url,
            HttpMethod.POST, httpEntity, responseClass);
        return responseEntity.getBody();
    }


    public static <T, D> T post(D d, String token, RestTemplate restTemplate, String url, ParameterizedTypeReference<T> responseClass) {
        final HttpEntity<D> httpEntity = getHttpEntity(d, token);
        final HttpEntity<T> responseEntity = restTemplate.exchange(url,
            HttpMethod.POST, httpEntity, responseClass);
        return responseEntity.getBody();
    }

    public static <T, D> T formUrlEncordedPost(D d, RestTemplate restTemplate, String url, ParameterizedTypeReference<T> responseClass) {
        final HttpEntity<D> httpEntity = getMultiValueHttpEntity(d);
        final HttpEntity<T> responseEntity = restTemplate.exchange(url,
            HttpMethod.POST, httpEntity, responseClass);
        return responseEntity.getBody();
    }

    public static <T, D> T put(D d, RestTemplate restTemplate, String url, Class<T> responseClass) {
        final HttpEntity<D> httpEntity = getHttpEntity(d);
        final HttpEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, responseClass);
        return responseEntity.getBody();
    }

    public static <T> T get(String url, RestTemplate restTemplate, ParameterizedTypeReference<T> responseClass) {
        final HttpEntity httpEntity = getHttpEntity();
        final HttpEntity<T> responseEntity = restTemplate.exchange(url,
            HttpMethod.GET, httpEntity, responseClass);
        return responseEntity.getBody();
    }

    public static <T> T get(RestTemplate restTemplate, String url, Class<T> responseClass) {
        final HttpEntity httpEntity = getHttpEntity();
        final HttpEntity<T> responseEntity = restTemplate.exchange(url,
            HttpMethod.GET, httpEntity, responseClass);
        return responseEntity.getBody();
    }


    public static <T> T delete(RestTemplate restTemplate, String url, Class<T> responseClass) {
        final HttpEntity httpEntity = getHttpEntity();
        final HttpEntity<T> responseEntity = restTemplate.exchange(url,
            HttpMethod.DELETE, httpEntity, responseClass);
        return responseEntity.getBody();
    }


    public static <T> T get(String token, String url, RestTemplate restTemplate, ParameterizedTypeReference<T> responseClass) {
        final HttpEntity httpEntity = getHttpEntity(token);
        final HttpEntity<T> responseEntity = restTemplate.exchange(url,
            HttpMethod.GET, httpEntity, responseClass);
        return responseEntity.getBody();
    }


    public static HttpHeaders getHttpHeaders() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return httpHeaders;
    }

    private static <D> HttpEntity<D> getHttpEntity(D t) {

        final HttpHeaders httpHeaders = getHttpHeaders();

        return new HttpEntity(t, httpHeaders);
    }

    private static <D> HttpEntity<D> getHttpEntity(D t, String token) {

        final HttpHeaders httpHeaders = getHttpHeaders(token);

        return new HttpEntity(t, httpHeaders);
    }

    private static HttpEntity getHttpEntity() {

        final HttpHeaders httpHeaders = getHttpHeaders();

        return new HttpEntity(httpHeaders);
    }

    private static HttpEntity getHttpEntity(String token) {

        final HttpHeaders httpHeaders = getHttpHeaders(token);

        return new HttpEntity(httpHeaders);
    }

    public static HttpHeaders getHttpHeaders(String token) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return httpHeaders;
    }


    public static HttpHeaders getFormEncodedHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString()); //Optional in case server sends back JSON data
        return headers;
    }

    private static <D> HttpEntity<D> getMultiValueHttpEntity(D payload) {
        return new HttpEntity<>(payload, getFormEncodedHeaders());
    }
}
