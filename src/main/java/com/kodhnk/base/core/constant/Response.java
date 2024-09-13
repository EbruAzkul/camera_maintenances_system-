package com.kodhnk.base.core.constant;

public enum Response {

    SUCCESS("Başarılı"),
    CREATED("Oluşturuldu"),
    REMOVED("Silindi"),
    UPDATED("Güncellendi"),
    DATALISTED("Veri Listelendi"),

    USER_CREATED("Kullanıcı oluşturuldu"),
    USER_UPDATED("Kullanıcı güncellendi"),
    USER_ALREADY_EXISTS("Kullanıcı zaten mevcut"),
    USER_NOT_CREATED("Kullanıcı oluşturulamadı"),

    LOGIN_SUCCESS("Giriş Başarılı"),
    LOGIN_FAILED("Giriş Başarısız"),

    ERROR("Hata"),
    NOT_FOUND("Bulunamadı"),
    UNAUTHORIZED("Yetkisiz"),
    FORBIDDEN("Yasak"),
    BAD_REQUEST("Geçersiz İstek"),
    INTERNAL_SERVER_ERROR("Sunucu Hatası"),
    USER_LIST("Kullanıcılar Listelendi"),
    USER_NOT_FOUND("Kullanıcı Bulunamadı"),


    GET_CAMERAMAINTENANCE("Bakımlar Listelendi"),
    CAMERAMAINTENANCE_NOT_FOUND("Yapılan Bakım Bulunamadı"),
    CREATE_CAMERAMAINTENANCE("Yeni Bakım Eklendi "),
    UPDATE_CAMERAMAINTENANCE("Yapılan Bakım Güncellendi"),
    DELETE_CAMERAMAINTENANCE("Yapılan Bakım Silindi"),

    GET_TECHNICIANS("Teknisyenler Listelendi"),
    GET_TECHNICIAN("Teknisyen Getirildi"),
    TECHNICIAN_NOT_FOUND("Teknisyen Bulunamadı"),
    CREATE_TECHNICIAN("Yeni Teknisyen Oluşturuldu"),
    UPDATE_TECHNICIAN("Teknisyen Güncellendi"),
    DELETE_TECHNICIAN("Teknisyen Silindi"),
    SPAREPART_LISTED("Yedek Parçalar Listelendi"),
    SPAREPART_FOUND("Yedek Parça Bulundu"),
    SPAREPART_NOT_FOUND("Yedek Parça Bulunamadı"),
    SPAREPART_CREATED("Yedek Parça Oluşturuldu"),
    SPAREPART_UPDATED("Yedek Parça Güncellendi"),
    SPAREPART_DELETED("Yedek Parça Silindi"),

    CAMERAZONE_LISTED("Kamera Alanları Listelendi"),
    CAMERAZONE_FOUND("Kamera Alanları Bulundu"),
    CAMERAZONE_NOT_FOUND("Kamera Alanları Bulunamadı"),
    CREATE_CAMERAZONE("Kamera Alanı oluşturuldu"),
    UPDATE_CAMERAZONE("Kamera Alanı Güncellendi"),
    DELETE_CAMERAZONE("Kamera Alanı Silindi"),

    CAMERANETWORK_GET_ALL("Tüm ağ getirildi"),
    CREATE_CAMERANETWORK("yeni kameraağı oluşturuldu"),
    CAMERANETWORK_NOT_FOUND("kameraağı bulunamadı"),
    UPDATE_CAMERANETWORK("kameraağı güncellendi"),
    DELETE_CAMERANETWORK("kameraağı silindi"),
    CAMERANETWORK_BY_ID("ağ getirildi"),
    EMAIL_ALREADY_EXISTS("Kayıt Bulunmaktadır."),

    GET_CAMERAINSPECTION("İncelemeler Getirildi"),
    CAMERAINSPECTION_NOT_FOUND("İnceleme Bulunamadı"),
    CREATE_CAMERAINSPECTION("Yeni İnceleme Oluşturuldu"),

    GET_CAMERA("Kameralar getirildi"),
    CAMERA_NOT_FOUND("Kamera Bulunamadı"),
    CREATE_CAMERA("Kamera Oluşturuldu"),
    UPDATE_CAMERA("Kamera Güncellendi"),
    DELETE_CAMERA("Kamera Silindi"),
    ROLE_NOT_FOUND("Rol Bulunamadı."),
    ROLE_LISTED("Roller Listelendi."),
    ROLE_CREATED("Rol Eklendi."),
    ROLE_UPDATED("Rol Güncellendi."),
    ROLE_DELETED("Rol Silindi."),
    ROLE_FOUND("Rol Getirildi."),
    ROLE_ADDED("rol eklendi"),
    GET_ADDRESS("Adresler Listelendi."),
    ADDRESS_NOT_FOUND("Adres Bulunamadı."),
    REQUEST_IS_NULL("İstek Boş."),
    CREATE_ADDRESS("Adres Eklendi."),
    UPDATE_ADDRESS("Adres Güncellendi."),
    DELETE_ADDRESS("Adres Silindi."),
    ROLE_ALREADY_EXISTS("Rol Zaten Mevcut."),
    CITY_IS_NULL("İl Alanı Boş."),
    DISTRICT_IS_NULL("İlçe Alanı Boş."),;

    private final String message;

    Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}