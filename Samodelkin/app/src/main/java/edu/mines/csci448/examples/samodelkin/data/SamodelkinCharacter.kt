package edu.mines.csci448.examples.samodelkin.data

import java.util.*

data class SamodelkinCharacter(val name: String,
                               val race: String,
                               val profession: String,
                               val dexterity: Int,
                               val wisdom: Int,
                               val strength: Int,
                               val intelligence: Int,
                               val constitution: Int,
                               val charisma: Int,
                               val avatarAssetPath: String? = null,
                               val id: UUID = UUID.randomUUID())