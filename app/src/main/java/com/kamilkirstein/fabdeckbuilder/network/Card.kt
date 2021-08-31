package com.kamilkirstein.fabdeckbuilder.network

import com.squareup.moshi.Json

/*
GET /cards/ARC000

Example response:

{
    "identifier":"ARC000",
    "name":"Eye of Ophidia",
    "rarity":"F",
    "keywords":["generic","resource","gem"],
    "stats":{"resource":"3"},
    "text":"**Legendary** *(You may only have 1 Eye of Ophidia in your deck.)*\n\nWhen you pitch Eye of Ophidia, **opt 2**.",
    "flavour":"Beyond the turbulent waters of Death\u2019s Knell, the ocean stretches as far as the eye can see, an endless, unfathomable expanse of deep blue. The keeper looks upon the shifting tides, feeling the weight of time within its weary soul. As the great divide draws near, it relinquishes a part of itself, bequeathing the gift of its immeasurable knowledge. Under the light of the pale moon, the ocean calls it home, and at last, the keeper sinks into the deep, undisturbed.",
    "comments":"The second fabled card to be released, the Eye of Ophidia helped to establish the pattern that there will be a Fabled card in every set. Additionally, it set a new standard in artwork for the franchise, with the Eye of Ophidia being a truly beautiful card.",
    "next":"ARC001",
    "prev":"ARC218"
}
 */
// another example :
/*
{"identifier":"absorb-in-aether-blue",
"name":"Absorb in Aether",
"keywords":["wizard","defense","reaction"],
"stats":{"cost":"1","defense":"2","resource":"3"},
"text":"The next card you play this turn with an effect that deals arcane damage, instead deals that much arcane damage plus 2.","rarity":"R","banned":false,"image":"https:\/\/fabdb2.imgix.net\/cards\/printings\/ARC125-RF.png?w=350&fit=clip&auto=compress","totalSideboard":0,"printings":[{"id":1095,"sku":{"sku":"ARC125-RF","finish":"rainbow","set":{"id":"arc","name":"Arcane Rising","released":"2020-03-27","browseable":true,"draftable":true},"number":"ARC125"},"set":"Arcane Rising","edition":{},"image":"https:\/\/fabdb2.imgix.net\/cards\/printings\/ARC125-RF.png?w=300&fit=clip&auto=compress"},{"id":1096,"sku":{"sku":"ARC125","finish":"regular","set":{"id":"arc","name":"Arcane Rising","released":"2020-03-27","browseable":true,"draftable":true},"number":"ARC125"},"set":"Arcane Rising","edition":{},"image":"https:\/\/fabdb2.imgix.net\/cards\/printings\/ARC125.png?w=300&fit=clip&auto=compress"},{"id":1513,"sku":{"sku":"U-ARC125-RF","finish":"rainbow","set":{"id":"u-arc","name":"Arcane Rising (Unlimited)","released":"2020-11-20","browseable":false},"number":"ARC125"},"set":"Arcane Rising Unlimited","edition":{},"image":"https:\/\/fabdb2.imgix.net\/cards\/printings\/U-ARC125-RF.png?w=300&fit=clip&auto=compress"},{"id":1514,"sku":{"sku":"U-ARC125","finish":"regular","set":{"id":"u-arc","name":"Arcane Rising (Unlimited)","released":"2020-11-20","browseable":false},"number":"ARC125"},"set":"Arcane Rising Unlimited","edition":{},"image":"https:\/\/fabdb2.imgix.net\/cards\/printings\/U-ARC125.png?w=300&fit=clip&auto=compress"}]},
 */





data class Card(
    val identifier: String,

    @Json(name ="identifier") val id : String,
    @Json(name = "name") val name: String,
    @Json(name = "image") val imgUrl: String
)