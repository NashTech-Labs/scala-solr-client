#Define Configuration in Solr :

Solr is a popular search platform for Web sites because it can index and search multiple sites and return recommendations for 
related content based on the search query's taxonomy.

As we discuss in [README.md](https://github.com/knoldus/scala-solr-client/blob/master/README.md) file, we can create **Core 
or Collection** into the Solr with defaulrt settings.

But If you want to create **Core** with your schema than you have to fullfil some basic requirement.

1. Cretae a ***configs*** folder which contains **conf folder** in it or download [***test_schema_configs***](https://github.com/knoldus/scala-solr-client/tree/master/example) folder for creating **Core** with schema and [***test_schemaless_configs***](https://github.com/knoldus/scala-solr-client/tree/master/example) folder for creating **Core** with *Schema less property* in your system.
2. If you are creating folder then there are some files which should be in the **conf folder** otherwise just go to the next step. We will discuss about these files later in this sagement.
  1. managed-schema
  2. solrconfig.xml
  3. elevate.xml
  4. stopwords.txt
  5. protwords.txt
  6. synonyms.txt
3. Now start Solr with the command which written below : `<base_dir>/bin/solr start -e cloud`
4. When you will start Solr with this command that means you have to provide all the configuration for the **Core**, so when it 
will ask for the configuration like : `Please choose a configuration for the anu_123 collection, available options are:`, there 
you have to provide the path of the *configs* folder.
5. You are ready with your new **Core** with your configuration and load data into it.
6. If you are going to run test cases now, then please make require changes in the ***application.conf*** file.

Now we will discuss about the those files which should be present in the **Conf folder** :

1. **managed-schema** : In this file you define the schema of your data. There are two type of schema: 
  1.  ***Classic Approach with Schema***: This file contains schema of the file which we provide while loading data into *Solr*.
  When we create *Schema* we have to provide some *FieldType* which is necessary like " tlongs, tdoubles,tdate, tdates" which is 
  present in *solr.Trie<datatype>Field" class because TrieField provides field types to support for Lucene's IntField, LongField, 
  FloatField and DoubleField. It supports integer, float, long, double and date types. eg: [Example of Schema](https://github.com/knoldus/scala-solr-client/blob/master/example/test_schema_configs/conf/managed-schema)
  2.  ***Schema Less Mode*** : Schemaless allow you to rapidly construct an effective schema. All these feature is construct via solrConfig.xml. Eg. [Example of Schemaless](https://github.com/knoldus/scala-solr-client/blob/master/example/test_schemaless_configs/conf/managed-schema).
  
  For More information regarding to the [Schemaless Mode](https://cwiki.apache.org/confluence/display/solr/Schemaless+Mode).
2. **solrconfig.xml** : This contains most of the parameters for configuring Solr itself. We does not make any change until we 
have to set any property. we basically set these property in this file :
  1. Data Directory Location
  2. Cache Parameters
  3. Request Handlers
  4. Search Components
  
  For more information please visit [Solr Config](https://wiki.apache.org/solr/SolrConfigXml).
3. **elevate.xml** : It enables you to configure the top results for a given query regardless of the normal lucene scoring. 
This component matches the user query text to a configured Map of top results. For more information please visit [The Query Elevation Component](https://cwiki.apache.org/confluence/display/solr/The+Query+Elevation+Component).
4. **stopwords.txt** : There are many words like "a", "the", or "is" can adversely effect search result relevancy. We can use 
stopwords.txt for ignoring the those words while indexing. For more information please visit [StopFilter](https://cwiki.apache.org/confluence/display/solr/Filter+Descriptions#FilterDescriptions-StopFilter).
5. **protwords.txt** : A customized protected word list may be specified with the "protected" attribute in the schema. Any 
words in the protected word list will not be modified by any stemmer in Solr. For more information please visit [protwords.txt](https://wiki.apache.org/solr/LanguageAnalysis#Customizing_Stemming)
6. **synonyms.txt** : Solr contains a filter factory called *SynonymFilterFactory* to achieve this functionality. You add your
synonyms in synonyms.txt file.For more information please visit [Synonym Filter](https://cwiki.apache.org/confluence/display/solr/Filter+Descriptions#FilterDescriptions-SynonymFilter).

These files should be present in your **conf folder** but only ***managed-schema*** & ***solrconfig.xml*** need to define and all of rest are not necessary to define. You can update those files according to your needs.
