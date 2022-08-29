package retrofit2;

import com.alibaba.android.arouter.utils.Consts;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.ParameterHandler;
import retrofit2.http.Body;
import retrofit2.http.C4028Header;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HTTP;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;
import retrofit2.http.Url;

final class ServiceMethod<R, T> {
    static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
    static final Pattern PARAM_NAME_REGEX = Pattern.compile(PARAM);
    static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
    private final HttpUrl baseUrl;
    private final CallAdapter<R, T> callAdapter;
    private final Call.Factory callFactory;
    private final MediaType contentType;
    private final boolean hasBody;
    private final Headers headers;
    private final String httpMethod;
    private final boolean isFormEncoded;
    private final boolean isMultipart;
    private final ParameterHandler<?>[] parameterHandlers;
    private final String relativeUrl;
    private final Converter<ResponseBody, R> responseConverter;

    ServiceMethod(Builder<R, T> builder) {
        this.callFactory = builder.retrofit.callFactory();
        this.callAdapter = builder.callAdapter;
        this.baseUrl = builder.retrofit.baseUrl();
        this.responseConverter = builder.responseConverter;
        this.httpMethod = builder.httpMethod;
        this.relativeUrl = builder.relativeUrl;
        this.headers = builder.headers;
        this.contentType = builder.contentType;
        this.hasBody = builder.hasBody;
        this.isFormEncoded = builder.isFormEncoded;
        this.isMultipart = builder.isMultipart;
        this.parameterHandlers = builder.parameterHandlers;
    }

    /* access modifiers changed from: package-private */
    public Call toCall(@Nullable Object... objArr) throws IOException {
        RequestBuilder requestBuilder = new RequestBuilder(this.httpMethod, this.baseUrl, this.relativeUrl, this.headers, this.contentType, this.hasBody, this.isFormEncoded, this.isMultipart);
        ParameterHandler<?>[] parameterHandlerArr = this.parameterHandlers;
        int length = objArr != null ? objArr.length : 0;
        if (length == parameterHandlerArr.length) {
            for (int i = 0; i < length; i++) {
                parameterHandlerArr[i].apply(requestBuilder, objArr[i]);
            }
            return this.callFactory.newCall(requestBuilder.build());
        }
        throw new IllegalArgumentException("Argument count (" + length + ") doesn't match expected count (" + parameterHandlerArr.length + ")");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [retrofit2.Call<R>, retrofit2.Call] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T adapt(retrofit2.Call<R> r2) {
        /*
            r1 = this;
            retrofit2.CallAdapter<R, T> r0 = r1.callAdapter
            java.lang.Object r2 = r0.adapt(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit2.ServiceMethod.adapt(retrofit2.Call):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    public R toResponse(ResponseBody responseBody) throws IOException {
        return this.responseConverter.convert(responseBody);
    }

    static final class Builder<T, R> {
        CallAdapter<T, R> callAdapter;
        MediaType contentType;
        boolean gotBody;
        boolean gotField;
        boolean gotPart;
        boolean gotPath;
        boolean gotQuery;
        boolean gotUrl;
        boolean hasBody;
        Headers headers;
        String httpMethod;
        boolean isFormEncoded;
        boolean isMultipart;
        final Method method;
        final Annotation[] methodAnnotations;
        final Annotation[][] parameterAnnotationsArray;
        ParameterHandler<?>[] parameterHandlers;
        final Type[] parameterTypes;
        String relativeUrl;
        Set<String> relativeUrlParamNames;
        Converter<ResponseBody, T> responseConverter;
        Type responseType;
        final Retrofit retrofit;

        Builder(Retrofit retrofit3, Method method2) {
            this.retrofit = retrofit3;
            this.method = method2;
            this.methodAnnotations = method2.getAnnotations();
            this.parameterTypes = method2.getGenericParameterTypes();
            this.parameterAnnotationsArray = method2.getParameterAnnotations();
        }

        public ServiceMethod build() {
            CallAdapter<T, R> createCallAdapter = createCallAdapter();
            this.callAdapter = createCallAdapter;
            Type responseType2 = createCallAdapter.responseType();
            this.responseType = responseType2;
            if (responseType2 == Response.class || responseType2 == Response.class) {
                throw methodError("'" + Utils.getRawType(this.responseType).getName() + "' is not a valid response body type. Did you mean ResponseBody?", new Object[0]);
            }
            this.responseConverter = createResponseConverter();
            for (Annotation parseMethodAnnotation : this.methodAnnotations) {
                parseMethodAnnotation(parseMethodAnnotation);
            }
            if (this.httpMethod != null) {
                if (!this.hasBody) {
                    if (this.isMultipart) {
                        throw methodError("Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                    } else if (this.isFormEncoded) {
                        throw methodError("FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                    }
                }
                int length = this.parameterAnnotationsArray.length;
                this.parameterHandlers = new ParameterHandler[length];
                int i = 0;
                while (i < length) {
                    Type type = this.parameterTypes[i];
                    if (!Utils.hasUnresolvableType(type)) {
                        Annotation[] annotationArr = this.parameterAnnotationsArray[i];
                        if (annotationArr != null) {
                            this.parameterHandlers[i] = parseParameter(i, type, annotationArr);
                            i++;
                        } else {
                            throw parameterError(i, "No Retrofit annotation found.", new Object[0]);
                        }
                    } else {
                        throw parameterError(i, "Parameter type must not include a type variable or wildcard: %s", type);
                    }
                }
                if (this.relativeUrl != null || this.gotUrl) {
                    boolean z = this.isFormEncoded;
                    if (!z && !this.isMultipart && !this.hasBody && this.gotBody) {
                        throw methodError("Non-body HTTP method cannot contain @Body.", new Object[0]);
                    } else if (z && !this.gotField) {
                        throw methodError("Form-encoded method must contain at least one @Field.", new Object[0]);
                    } else if (!this.isMultipart || this.gotPart) {
                        return new ServiceMethod(this);
                    } else {
                        throw methodError("Multipart method must contain at least one @Part.", new Object[0]);
                    }
                } else {
                    throw methodError("Missing either @%s URL or @Url parameter.", this.httpMethod);
                }
            } else {
                throw methodError("HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
            }
        }

        private CallAdapter<T, R> createCallAdapter() {
            Type genericReturnType = this.method.getGenericReturnType();
            if (Utils.hasUnresolvableType(genericReturnType)) {
                throw methodError("Method return type must not include a type variable or wildcard: %s", genericReturnType);
            } else if (genericReturnType != Void.TYPE) {
                try {
                    return this.retrofit.callAdapter(genericReturnType, this.method.getAnnotations());
                } catch (RuntimeException e) {
                    throw methodError(e, "Unable to create call adapter for %s", genericReturnType);
                }
            } else {
                throw methodError("Service methods cannot return void.", new Object[0]);
            }
        }

        private void parseMethodAnnotation(Annotation annotation) {
            if (annotation instanceof DELETE) {
                parseHttpMethodAndPath("DELETE", ((DELETE) annotation).value(), false);
            } else if (annotation instanceof GET) {
                parseHttpMethodAndPath("GET", ((GET) annotation).value(), false);
            } else if (annotation instanceof HEAD) {
                parseHttpMethodAndPath("HEAD", ((HEAD) annotation).value(), false);
                if (!Void.class.equals(this.responseType)) {
                    throw methodError("HEAD method must use Void as response type.", new Object[0]);
                }
            } else if (annotation instanceof PATCH) {
                parseHttpMethodAndPath("PATCH", ((PATCH) annotation).value(), true);
            } else if (annotation instanceof POST) {
                parseHttpMethodAndPath("POST", ((POST) annotation).value(), true);
            } else if (annotation instanceof PUT) {
                parseHttpMethodAndPath("PUT", ((PUT) annotation).value(), true);
            } else if (annotation instanceof OPTIONS) {
                parseHttpMethodAndPath("OPTIONS", ((OPTIONS) annotation).value(), false);
            } else if (annotation instanceof HTTP) {
                HTTP http = (HTTP) annotation;
                parseHttpMethodAndPath(http.method(), http.path(), http.hasBody());
            } else if (annotation instanceof retrofit2.http.Headers) {
                String[] value = ((retrofit2.http.Headers) annotation).value();
                if (value.length != 0) {
                    this.headers = parseHeaders(value);
                    return;
                }
                throw methodError("@Headers annotation is empty.", new Object[0]);
            } else if (annotation instanceof Multipart) {
                if (!this.isFormEncoded) {
                    this.isMultipart = true;
                    return;
                }
                throw methodError("Only one encoding annotation is allowed.", new Object[0]);
            } else if (!(annotation instanceof FormUrlEncoded)) {
            } else {
                if (!this.isMultipart) {
                    this.isFormEncoded = true;
                    return;
                }
                throw methodError("Only one encoding annotation is allowed.", new Object[0]);
            }
        }

        private void parseHttpMethodAndPath(String str, String str2, boolean z) {
            String str3 = this.httpMethod;
            if (str3 == null) {
                this.httpMethod = str;
                this.hasBody = z;
                if (!str2.isEmpty()) {
                    int indexOf = str2.indexOf(63);
                    if (indexOf != -1 && indexOf < str2.length() - 1) {
                        String substring = str2.substring(indexOf + 1);
                        if (ServiceMethod.PARAM_URL_REGEX.matcher(substring).find()) {
                            throw methodError("URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", substring);
                        }
                    }
                    this.relativeUrl = str2;
                    this.relativeUrlParamNames = ServiceMethod.parsePathParameters(str2);
                    return;
                }
                return;
            }
            throw methodError("Only one HTTP method is allowed. Found: %s and %s.", str3, str);
        }

        private Headers parseHeaders(String[] strArr) {
            Headers.Builder builder = new Headers.Builder();
            for (String str : strArr) {
                int indexOf = str.indexOf(58);
                if (indexOf == -1 || indexOf == 0 || indexOf == str.length() - 1) {
                    throw methodError("@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str);
                }
                String substring = str.substring(0, indexOf);
                String trim = str.substring(indexOf + 1).trim();
                if (HttpHeaders.CONTENT_TYPE.equalsIgnoreCase(substring)) {
                    MediaType parse = MediaType.parse(trim);
                    if (parse != null) {
                        this.contentType = parse;
                    } else {
                        throw methodError("Malformed content type: %s", trim);
                    }
                } else {
                    builder.add(substring, trim);
                }
            }
            return builder.build();
        }

        private ParameterHandler<?> parseParameter(int i, Type type, Annotation[] annotationArr) {
            ParameterHandler<?> parameterHandler = null;
            for (Annotation parseParameterAnnotation : annotationArr) {
                ParameterHandler<?> parseParameterAnnotation2 = parseParameterAnnotation(i, type, annotationArr, parseParameterAnnotation);
                if (parseParameterAnnotation2 != null) {
                    if (parameterHandler == null) {
                        parameterHandler = parseParameterAnnotation2;
                    } else {
                        throw parameterError(i, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
                    }
                }
            }
            if (parameterHandler != null) {
                return parameterHandler;
            }
            throw parameterError(i, "No Retrofit annotation found.", new Object[0]);
        }

        private ParameterHandler<?> parseParameterAnnotation(int i, Type type, Annotation[] annotationArr, Annotation annotation) {
            if (annotation instanceof Url) {
                if (this.gotUrl) {
                    throw parameterError(i, "Multiple @Url method annotations found.", new Object[0]);
                } else if (this.gotPath) {
                    throw parameterError(i, "@Path parameters may not be used with @Url.", new Object[0]);
                } else if (this.gotQuery) {
                    throw parameterError(i, "A @Url parameter must not come after a @Query", new Object[0]);
                } else if (this.relativeUrl == null) {
                    this.gotUrl = true;
                    if (type == HttpUrl.class || type == String.class || type == URI.class || ((type instanceof Class) && "android.net.Uri".equals(((Class) type).getName()))) {
                        return new ParameterHandler.RelativeUrl();
                    }
                    throw parameterError(i, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
                } else {
                    throw parameterError(i, "@Url cannot be used with @%s URL", this.httpMethod);
                }
            } else if (annotation instanceof Path) {
                if (this.gotQuery) {
                    throw parameterError(i, "A @Path parameter must not come after a @Query.", new Object[0]);
                } else if (this.gotUrl) {
                    throw parameterError(i, "@Path parameters may not be used with @Url.", new Object[0]);
                } else if (this.relativeUrl != null) {
                    this.gotPath = true;
                    Path path = (Path) annotation;
                    String value = path.value();
                    validatePathName(i, value);
                    return new ParameterHandler.Path(value, this.retrofit.stringConverter(type, annotationArr), path.encoded());
                } else {
                    throw parameterError(i, "@Path can only be used with relative url on @%s", this.httpMethod);
                }
            } else if (annotation instanceof Query) {
                Query query = (Query) annotation;
                String value2 = query.value();
                boolean encoded = query.encoded();
                Class<?> rawType = Utils.getRawType(type);
                this.gotQuery = true;
                if (Iterable.class.isAssignableFrom(rawType)) {
                    if (type instanceof ParameterizedType) {
                        return new ParameterHandler.Query(value2, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), encoded).iterable();
                    }
                    throw parameterError(i, rawType.getSimpleName() + " must include generic type (e.g., " + rawType.getSimpleName() + "<String>)", new Object[0]);
                } else if (!rawType.isArray()) {
                    return new ParameterHandler.Query(value2, this.retrofit.stringConverter(type, annotationArr), encoded);
                } else {
                    return new ParameterHandler.Query(value2, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType.getComponentType()), annotationArr), encoded).array();
                }
            } else if (annotation instanceof QueryName) {
                boolean encoded2 = ((QueryName) annotation).encoded();
                Class<?> rawType2 = Utils.getRawType(type);
                this.gotQuery = true;
                if (Iterable.class.isAssignableFrom(rawType2)) {
                    if (type instanceof ParameterizedType) {
                        return new ParameterHandler.QueryName(this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), encoded2).iterable();
                    }
                    throw parameterError(i, rawType2.getSimpleName() + " must include generic type (e.g., " + rawType2.getSimpleName() + "<String>)", new Object[0]);
                } else if (!rawType2.isArray()) {
                    return new ParameterHandler.QueryName(this.retrofit.stringConverter(type, annotationArr), encoded2);
                } else {
                    return new ParameterHandler.QueryName(this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType2.getComponentType()), annotationArr), encoded2).array();
                }
            } else if (annotation instanceof QueryMap) {
                Class<?> rawType3 = Utils.getRawType(type);
                if (Map.class.isAssignableFrom(rawType3)) {
                    Type supertype = Utils.getSupertype(type, rawType3, Map.class);
                    if (supertype instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) supertype;
                        Type parameterUpperBound = Utils.getParameterUpperBound(0, parameterizedType);
                        if (String.class == parameterUpperBound) {
                            return new ParameterHandler.QueryMap(this.retrofit.stringConverter(Utils.getParameterUpperBound(1, parameterizedType), annotationArr), ((QueryMap) annotation).encoded());
                        }
                        throw parameterError(i, "@QueryMap keys must be of type String: " + parameterUpperBound, new Object[0]);
                    }
                    throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                throw parameterError(i, "@QueryMap parameter type must be Map.", new Object[0]);
            } else if (annotation instanceof C4028Header) {
                String value3 = ((C4028Header) annotation).value();
                Class<?> rawType4 = Utils.getRawType(type);
                if (Iterable.class.isAssignableFrom(rawType4)) {
                    if (type instanceof ParameterizedType) {
                        return new ParameterHandler.C4026Header(value3, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr)).iterable();
                    }
                    throw parameterError(i, rawType4.getSimpleName() + " must include generic type (e.g., " + rawType4.getSimpleName() + "<String>)", new Object[0]);
                } else if (!rawType4.isArray()) {
                    return new ParameterHandler.C4026Header(value3, this.retrofit.stringConverter(type, annotationArr));
                } else {
                    return new ParameterHandler.C4026Header(value3, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType4.getComponentType()), annotationArr)).array();
                }
            } else if (annotation instanceof HeaderMap) {
                Class<?> rawType5 = Utils.getRawType(type);
                if (Map.class.isAssignableFrom(rawType5)) {
                    Type supertype2 = Utils.getSupertype(type, rawType5, Map.class);
                    if (supertype2 instanceof ParameterizedType) {
                        ParameterizedType parameterizedType2 = (ParameterizedType) supertype2;
                        Type parameterUpperBound2 = Utils.getParameterUpperBound(0, parameterizedType2);
                        if (String.class == parameterUpperBound2) {
                            return new ParameterHandler.HeaderMap(this.retrofit.stringConverter(Utils.getParameterUpperBound(1, parameterizedType2), annotationArr));
                        }
                        throw parameterError(i, "@HeaderMap keys must be of type String: " + parameterUpperBound2, new Object[0]);
                    }
                    throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                throw parameterError(i, "@HeaderMap parameter type must be Map.", new Object[0]);
            } else if (annotation instanceof Field) {
                if (this.isFormEncoded) {
                    Field field = (Field) annotation;
                    String value4 = field.value();
                    boolean encoded3 = field.encoded();
                    this.gotField = true;
                    Class<?> rawType6 = Utils.getRawType(type);
                    if (Iterable.class.isAssignableFrom(rawType6)) {
                        if (type instanceof ParameterizedType) {
                            return new ParameterHandler.Field(value4, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), encoded3).iterable();
                        }
                        throw parameterError(i, rawType6.getSimpleName() + " must include generic type (e.g., " + rawType6.getSimpleName() + "<String>)", new Object[0]);
                    } else if (!rawType6.isArray()) {
                        return new ParameterHandler.Field(value4, this.retrofit.stringConverter(type, annotationArr), encoded3);
                    } else {
                        return new ParameterHandler.Field(value4, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType6.getComponentType()), annotationArr), encoded3).array();
                    }
                } else {
                    throw parameterError(i, "@Field parameters can only be used with form encoding.", new Object[0]);
                }
            } else if (annotation instanceof FieldMap) {
                if (this.isFormEncoded) {
                    Class<?> rawType7 = Utils.getRawType(type);
                    if (Map.class.isAssignableFrom(rawType7)) {
                        Type supertype3 = Utils.getSupertype(type, rawType7, Map.class);
                        if (supertype3 instanceof ParameterizedType) {
                            ParameterizedType parameterizedType3 = (ParameterizedType) supertype3;
                            Type parameterUpperBound3 = Utils.getParameterUpperBound(0, parameterizedType3);
                            if (String.class == parameterUpperBound3) {
                                Converter stringConverter = this.retrofit.stringConverter(Utils.getParameterUpperBound(1, parameterizedType3), annotationArr);
                                this.gotField = true;
                                return new ParameterHandler.FieldMap(stringConverter, ((FieldMap) annotation).encoded());
                            }
                            throw parameterError(i, "@FieldMap keys must be of type String: " + parameterUpperBound3, new Object[0]);
                        }
                        throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                    }
                    throw parameterError(i, "@FieldMap parameter type must be Map.", new Object[0]);
                }
                throw parameterError(i, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
            } else if (annotation instanceof Part) {
                if (this.isMultipart) {
                    Part part = (Part) annotation;
                    this.gotPart = true;
                    String value5 = part.value();
                    Class<?> rawType8 = Utils.getRawType(type);
                    if (!value5.isEmpty()) {
                        Headers of = Headers.m1012of(HttpHeaders.CONTENT_DISPOSITION, "form-data; name=\"" + value5 + "\"", "Content-Transfer-Encoding", part.encoding());
                        if (Iterable.class.isAssignableFrom(rawType8)) {
                            if (type instanceof ParameterizedType) {
                                Type parameterUpperBound4 = Utils.getParameterUpperBound(0, (ParameterizedType) type);
                                if (!MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(parameterUpperBound4))) {
                                    return new ParameterHandler.Part(of, this.retrofit.requestBodyConverter(parameterUpperBound4, annotationArr, this.methodAnnotations)).iterable();
                                }
                                throw parameterError(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                            }
                            throw parameterError(i, rawType8.getSimpleName() + " must include generic type (e.g., " + rawType8.getSimpleName() + "<String>)", new Object[0]);
                        } else if (rawType8.isArray()) {
                            Class<?> boxIfPrimitive = ServiceMethod.boxIfPrimitive(rawType8.getComponentType());
                            if (!MultipartBody.Part.class.isAssignableFrom(boxIfPrimitive)) {
                                return new ParameterHandler.Part(of, this.retrofit.requestBodyConverter(boxIfPrimitive, annotationArr, this.methodAnnotations)).array();
                            }
                            throw parameterError(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                        } else if (!MultipartBody.Part.class.isAssignableFrom(rawType8)) {
                            return new ParameterHandler.Part(of, this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations));
                        } else {
                            throw parameterError(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                        }
                    } else if (Iterable.class.isAssignableFrom(rawType8)) {
                        if (!(type instanceof ParameterizedType)) {
                            throw parameterError(i, rawType8.getSimpleName() + " must include generic type (e.g., " + rawType8.getSimpleName() + "<String>)", new Object[0]);
                        } else if (MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(Utils.getParameterUpperBound(0, (ParameterizedType) type)))) {
                            return ParameterHandler.RawPart.INSTANCE.iterable();
                        } else {
                            throw parameterError(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                        }
                    } else if (rawType8.isArray()) {
                        if (MultipartBody.Part.class.isAssignableFrom(rawType8.getComponentType())) {
                            return ParameterHandler.RawPart.INSTANCE.array();
                        }
                        throw parameterError(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    } else if (MultipartBody.Part.class.isAssignableFrom(rawType8)) {
                        return ParameterHandler.RawPart.INSTANCE;
                    } else {
                        throw parameterError(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    }
                } else {
                    throw parameterError(i, "@Part parameters can only be used with multipart encoding.", new Object[0]);
                }
            } else if (annotation instanceof PartMap) {
                if (this.isMultipart) {
                    this.gotPart = true;
                    Class<?> rawType9 = Utils.getRawType(type);
                    if (Map.class.isAssignableFrom(rawType9)) {
                        Type supertype4 = Utils.getSupertype(type, rawType9, Map.class);
                        if (supertype4 instanceof ParameterizedType) {
                            ParameterizedType parameterizedType4 = (ParameterizedType) supertype4;
                            Type parameterUpperBound5 = Utils.getParameterUpperBound(0, parameterizedType4);
                            if (String.class == parameterUpperBound5) {
                                Type parameterUpperBound6 = Utils.getParameterUpperBound(1, parameterizedType4);
                                if (!MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(parameterUpperBound6))) {
                                    return new ParameterHandler.PartMap(this.retrofit.requestBodyConverter(parameterUpperBound6, annotationArr, this.methodAnnotations), ((PartMap) annotation).encoding());
                                }
                                throw parameterError(i, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
                            }
                            throw parameterError(i, "@PartMap keys must be of type String: " + parameterUpperBound5, new Object[0]);
                        }
                        throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                    }
                    throw parameterError(i, "@PartMap parameter type must be Map.", new Object[0]);
                }
                throw parameterError(i, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
            } else if (!(annotation instanceof Body)) {
                return null;
            } else {
                if (this.isFormEncoded || this.isMultipart) {
                    throw parameterError(i, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
                } else if (!this.gotBody) {
                    try {
                        Converter requestBodyConverter = this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations);
                        this.gotBody = true;
                        return new ParameterHandler.Body(requestBodyConverter);
                    } catch (RuntimeException e) {
                        throw parameterError(e, i, "Unable to create @Body converter for %s", type);
                    }
                } else {
                    throw parameterError(i, "Multiple @Body method annotations found.", new Object[0]);
                }
            }
        }

        private void validatePathName(int i, String str) {
            if (!ServiceMethod.PARAM_NAME_REGEX.matcher(str).matches()) {
                throw parameterError(i, "@Path parameter name must match %s. Found: %s", ServiceMethod.PARAM_URL_REGEX.pattern(), str);
            } else if (!this.relativeUrlParamNames.contains(str)) {
                throw parameterError(i, "URL \"%s\" does not contain \"{%s}\".", this.relativeUrl, str);
            }
        }

        private Converter<ResponseBody, T> createResponseConverter() {
            try {
                return this.retrofit.responseBodyConverter(this.responseType, this.method.getAnnotations());
            } catch (RuntimeException e) {
                throw methodError(e, "Unable to create converter for %s", this.responseType);
            }
        }

        private RuntimeException methodError(String str, Object... objArr) {
            return methodError((Throwable) null, str, objArr);
        }

        private RuntimeException methodError(Throwable th, String str, Object... objArr) {
            String format = String.format(str, objArr);
            return new IllegalArgumentException(format + "\n    for method " + this.method.getDeclaringClass().getSimpleName() + Consts.DOT + this.method.getName(), th);
        }

        private RuntimeException parameterError(Throwable th, int i, String str, Object... objArr) {
            return methodError(th, str + " (parameter #" + (i + 1) + ")", objArr);
        }

        private RuntimeException parameterError(int i, String str, Object... objArr) {
            return methodError(str + " (parameter #" + (i + 1) + ")", objArr);
        }
    }

    static Set<String> parsePathParameters(String str) {
        Matcher matcher = PARAM_URL_REGEX.matcher(str);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (matcher.find()) {
            linkedHashSet.add(matcher.group(1));
        }
        return linkedHashSet;
    }

    static Class<?> boxIfPrimitive(Class<?> cls) {
        if (Boolean.TYPE == cls) {
            return Boolean.class;
        }
        if (Byte.TYPE == cls) {
            return Byte.class;
        }
        if (Character.TYPE == cls) {
            return Character.class;
        }
        if (Double.TYPE == cls) {
            return Double.class;
        }
        if (Float.TYPE == cls) {
            return Float.class;
        }
        if (Integer.TYPE == cls) {
            return Integer.class;
        }
        if (Long.TYPE == cls) {
            return Long.class;
        }
        return Short.TYPE == cls ? Short.class : cls;
    }
}
