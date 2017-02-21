package com.knoldus.solr

import org.scalatest.FunSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SolrClientSearchSuite extends FunSpec {

  val scc = new SolrClientSearch()


  it("Fetch Total count From Solr Client ") {
    val expected_result = 4
    val record = scc.findCountOfRecord()
    assert(record.get >= expected_result)
  }

  it("fetch data with keyword") {
    val keyword = "fantasy"
    val expected_result = 3
    val record = scc.findRecordWithKeyword(keyword)
    assert(record.get equals expected_result)

  }

  it("fetch data with key value") {
    val key = "name"
    val value = "The Sea of Monsters"
    val expected_result = 1
    val record = scc.findRecordWithKeyAndValue(key, value)
    assert(record.get equals expected_result)

  }

  it("insert data") {
    val book_Details = new Book_Details("125436-145236-1244", Array("book", "education"),
      "Scala", "Martin", Some("scala education"),
      2, "education", true, 1253.2, 892)
    val record = scc.createOrUpdateRecord(book_Details)
    assert(record.get equals 0)
  }

  it("update data") {
    val book_Details = new Book_Details("123456789-00-09876", Array("book", "education"),
      "Akka_basic", "Martin", Some("scala education"),
      2, "education", true, 1253.2, 892)

    val book_Details_new = new Book_Details("123456789-00-09876", Array("book", "education"),
      "Akka_http", "Martin", Some("scala education"),
      2, "education", true, 1253.2, 892)
    scc.createOrUpdateRecord(book_Details)
    val record = scc.createOrUpdateRecord(book_Details_new)
    assert(record.get equals 0)
  }
}
