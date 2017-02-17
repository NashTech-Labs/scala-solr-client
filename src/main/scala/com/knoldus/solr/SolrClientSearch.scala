package com.knoldus.solr

import java.io.IOException

import org.apache.solr.client.solrj.{SolrQuery, SolrServerException}
import org.apache.solr.client.solrj.impl.HttpSolrClient
import org.apache.solr.client.solrj.response.{QueryResponse, UpdateResponse}
import org.apache.solr.common.{SolrDocumentList, SolrInputDocument}
import com.typesafe.config.ConfigFactory

/**
 * Created by anurag on 10/2/17.
 */


case class Book_Details(id: String,
    cat: Array[String],
    name: String,
    author: String,
    series_t: String,
    sequence_i: Int,
    genre_s: String,
    inStock: Boolean,
    price: Double,
    pages_i: Int)


class SolrClientSearch {

  val config = ConfigFactory.load("application.conf")
  val url = config.getString("solr.url")
  val collection_name = config.getString("solr.collection")
  val url_final = url + collection_name
  println("************************* Full url " + url_final)

  def findCountOfRecord(): Option[Int] = {
    try {
      val solrClient = new HttpSolrClient.Builder(url_final).build()
      val parameter = new SolrQuery()
      parameter.set("qt", "/select")
      parameter.set("indent", "on")
      parameter.set("q", "*:*")
      parameter.set("wt", "json")
      val response: QueryResponse = solrClient.query(parameter)
      val documentList: SolrDocumentList = response.getResults
      println(" Result List Data " + documentList.size())
      println("**************** " + documentList.get(1))
      Some(documentList.size())
    } catch {
      case solrServerException: SolrServerException =>
        println("Solr Server Exception : " + solrServerException.getMessage)
        None
    }
  }

  def findRecordWithKeyword(keyword: String) = {
    try {
      val solrClient = new HttpSolrClient.Builder(url_final).build()
      val parameter = new SolrQuery()
      parameter.set("qt", "/select")
      parameter.set("indent", "on")
      parameter.set("q", s"$keyword")
      parameter.set("wt", "json")
      val response: QueryResponse = solrClient.query(parameter)
      val documentList: SolrDocumentList = response.getResults
      println(" Result List Data " + documentList.size())
      Some(documentList.size())
    } catch {
      case solrServerException: SolrServerException =>
        println("Solr Server Exception : " + solrServerException.getMessage)
        None
    }
  }

  def findRecordWithKeyAndValue(key: String, value: String): Option[Int] = {
    try {
      val solrClient = new HttpSolrClient.Builder(url_final).build()
      val keyValue = s"$key:" + s"${ value.trim }"
      val parameter = new SolrQuery()
      parameter.set("qt", "/select")
      parameter.set("indent", "true")
      parameter.set("q", s"$keyValue")
      parameter.set("wt", "json")
      val response: QueryResponse = solrClient.query(parameter)
      val documentList: SolrDocumentList = response.getResults
      println(" Result List Data " + documentList.size())
      println("**************** " + documentList.get(0))
      Some(documentList.size())
    } catch {
      case solrServerException: SolrServerException =>
        println("Solr Server Exception : " + solrServerException.getMessage)
        None
    }
  }

  def updateRecord(book_Details: Book_Details): Option[Int] = {
    try {
      val solrClient = new HttpSolrClient.Builder(url).build()
      val list_cat = Array("book", "hardcover")
      val sdoc = new SolrInputDocument()
      sdoc.addField("id", book_Details.id)
      sdoc.addField("cat", book_Details.cat)
      sdoc.addField("name", book_Details.name)
      sdoc.addField("author", book_Details.author)
      sdoc.addField("series_t", book_Details.series_t)
      sdoc.addField("sequence_i", book_Details.sequence_i)
      sdoc.addField("genre_s", book_Details.genre_s)
      sdoc.addField("inStock", book_Details.inStock)
      sdoc.addField("price", book_Details.price)
      sdoc.addField("pages_i", book_Details.pages_i)
      val result: UpdateResponse = solrClient.add(collection_name, sdoc)
      Some(result.getStatus)
    } catch {
      case solrServerException: SolrServerException =>
        println("Solr Server Exception : " + solrServerException.getMessage)
        None
    }
  }
}
