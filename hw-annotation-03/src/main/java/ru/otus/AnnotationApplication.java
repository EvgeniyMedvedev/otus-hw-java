/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2020 VTB Group. All rights reserved.
 */

package ru.otus;

import static ru.otus.provider.AnnotationProvider.test;

import ru.otus.test.AnnotationTest;

/**
 * AnnotationApplication.
 *
 * @author Evgeniy_Medvedev
 */
public class AnnotationApplication {

    public static void main(String[] args) {
        test(AnnotationTest.class);
    }
}