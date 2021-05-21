package uz.pdp.demo.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import uz.pdp.demo.exception.BadRequestException;

import java.sql.Timestamp;
import java.util.HashMap;

public class CommonUtils {
    public static void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Sahifa soni noldan kam bo'lishi mumkin emas.");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Sahifa soni " + AppConstants.MAX_PAGE_SIZE + " dan ko'p bo'lishi mumkin emas.");
        }
    }

    public static Pageable getPageable(int page, int size, String byField) {
        validatePageNumberAndSize(page, size);
        return PageRequest.of(page, size, Sort.Direction.DESC, byField);
    }

    public static Pageable getPageable(int page, int size) {
        validatePageNumberAndSize(page, size);
        return PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
    }
    public static Pageable getPageableForNative(int page, int size) {
        validatePageNumberAndSize(page, size);
        return PageRequest.of(page, size, Sort.Direction.DESC, "created_at");
    }

    public static Timestamp validTimestamp(Timestamp timestamp, Boolean isFrom) {
        if (isFrom)
            return timestamp == null ? new Timestamp(1) : timestamp;
        return timestamp == null ? new Timestamp(System.currentTimeMillis()) : timestamp;
    }
    public static Object parseHashMapToObject(HashMap map, Class cls) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String jsonString = gson.toJson(map);
        return gson.fromJson(jsonString, cls);
    }
    public static Object parseStringToObject(String jsonString, Class cls) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(jsonString, cls);
    }
}
