package com.affenbande.affenbandeBackend.controller.old


const val BEARWALK_DESCR = TestConstants.BEARWALK_DESCRIPT_1 + "\n" + TestConstants.BEARWALK_DESCRIPT_2


val exampleSport = mapOf(
    "name" to "Turnen",
    "imageFile" to TestConstants.EXAMPLE_SPORT_IMAGE,
)

val exampleSubcat = mapOf(
    "name" to "Saltos",
    "sports" to listOf("Parkour", "Turnen", "Breakdance", "Capoeira"),
    "imagePath" to TestConstants.EXAMPLE_SUBCAT_IMAGE,

    )


val exampleMove = mapOf(
    "name" to "Bärenlauf",
    "description" to BEARWALK_DESCR,
    "imageFile" to TestConstants.EXAMPLE_MOVE_IMAGE,
    "subcategories" to listOf("Elemente", "Animalwalks"),
    "sports" to listOf("Movement"),
    "level" to 30,
    "intensity" to 5,
    "preMoves" to listOf("Baer"),
    "optPreMoves" to listOf<String>(),
    "timePreparation" to 5,
    "timeExercise" to 30,
    "setFormula" to "x",
    )

