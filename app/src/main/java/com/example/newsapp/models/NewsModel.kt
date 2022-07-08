package com.example.newsapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * MODEL LAYER
 * News model 'POJO' for fetching data from the server
 * created by Abanoub on 7/7/2022
 */
data class NewsModel(

    @SerializedName("section")
    val section: String,

    @SerializedName("subsection")
    val subsection: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("abstract")
    val abstract: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("uri")
    val uri: String,

    @SerializedName("byline")
    val byline: String,

    @SerializedName("item_type")
    val itemType: String,

    @SerializedName("updated_date")
    val updatedDate: String,

    @SerializedName("created_date")
    val createdDate: String,

    @SerializedName("published_date")
    val publishedDate: String,

    @SerializedName("material_type_facet")
    val materialTypeFacet: String,

    @SerializedName("kicker")
    val kicker: String,

    @SerializedName("des_facet")
    val desFacet: List<String>,

    @SerializedName("org_facet")
    val orgFacet: List<String>,

    @SerializedName("per_facet")
    val perFacet: List<String>,

    @SerializedName("geo_facet")
    val geoFacet: List<String?>,

    @SerializedName("multimedia")
    val multimedia: List<Multimedia>,

    @SerializedName("short_url")
    val shortURL: String
): Serializable

data class Multimedia (

    @SerializedName("url")
    val url: String,

    @SerializedName("format")
    val format: String,
): Serializable