package com.knoldus.solr

import org.scalatest.FunSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SolrClientSearchSuite extends FunSpec {

 val scc = new SolrClientSearch()

   it("Fetch Total count From Solr Client ") {
     val collection_name = "gettingstarted"
     val expected_result = 4
     val record = scc.findCountOfRecord(collection_name)
     assert(record.get >= expected_result)
   }

   it("fetch data with keyword") {
     val collection_name = "gettingstarted"
     val keyword = "fantasy"
     val expected_result = 3
     val record = scc.findRecordWithKeyword(collection_name,keyword)
     assert(record.get equals expected_result)

   }

   it("fetch data with key value") {
     val collection_name = "gettingstarted"
     val key = "name"
     val value = "The Sea of Monsters"
     val expected_result = 1
     val record = scc.findRecordWithKeyAndValue(collection_name,key, value)
     assert(record.get equals expected_result)

   }

  it("update or insert data") {
    val book_Details = new Book_Details("123456789-0-09876",Array("book", "education"),"scala","Martin","scala education",
      2,"education",true,1253.2,892)
    val collection_name = "gettingstarted"
    val record = scc.updateRecord(collection_name, book_Details)
    assert(record.get equals 0)
  }
}
