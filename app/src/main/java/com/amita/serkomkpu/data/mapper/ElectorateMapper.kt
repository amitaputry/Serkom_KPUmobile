package com.amita.serkomkpu.data.mapper

import com.amita.serkomkpu.data.local.ElectorateEntity
import com.amita.serkomkpu.model.Electorate

fun Electorate.toEntity(): ElectorateEntity =
    ElectorateEntity(
        id = id,
        image = image,
        nik = nik,
        name = name,
        phone = phone,
        gender = gender,
        dateCollectionDate = dateCollectionDate,
        address = address
    )

fun List<ElectorateEntity>.toDomains(): List<Electorate> =
    map { it.toDomain() }

fun ElectorateEntity.toDomain(): Electorate =
    Electorate(
        id = id,
        image = image,
        nik = nik,
        name = name,
        phone = phone,
        gender = gender,
        dateCollectionDate = dateCollectionDate,
        address = address
    )
