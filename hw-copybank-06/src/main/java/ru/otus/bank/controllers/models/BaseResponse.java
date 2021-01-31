package ru.otus.bank.controllers.models;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * BaseResponse.
 *
 * @author Evgeniy_Medvedev
 */
@Data
public class BaseResponse {

    private int value;

    private String msg;
}