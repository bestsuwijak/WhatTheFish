package buu.informatics.s59160141.whatthefish.database

import androidx.room.*
import buu.informatics.s59160141.whatthefish.models.*

data class DatabaseAll(
    @Embedded val databaseFish: DatabaseFishes,
    @Relation(parentColumn = "databaseFishId", entityColumn = "thNameOwnerId")
    var thNames: List<DatabaseThNames>,
    @Relation(parentColumn = "databaseFishId", entityColumn = "engNameOwnerId")
    var engNames: List<DatabaseEngNames>,
    @Relation(parentColumn = "databaseFishId", entityColumn = "textureImageOwnerId")
    var textureImages: List<DatabaseTextureImages>,
    @Relation(parentColumn = "databaseFishId", entityColumn = "imageOwnerId")
    var images: List<DatabaseImages>
)

@Entity(tableName = "database_fishes")
data class DatabaseFishes(
    @PrimaryKey val databaseFishId: String,
    var scienceName: String,
    var appearance: String,
    var habitat: String,
    var dissemination: String,
    var editedAt: String,
    var createdAt: String,
    @Embedded var model: DatabaseModel,
    @Embedded var icon: DatabaseIcon,
    var number: String,
    var status: Int,
    var foundFish: Boolean = false,
    var stringDateFound: String = "",
    var stringTimeFound: String = " "

)

@Entity(tableName = "th_names")
data class DatabaseThNames(
    @PrimaryKey val thNameId: String,
    var thNameOwnerId: String,
    @ColumnInfo(name = "th_name") var thName: String
)

@Entity(tableName = "eng_names")
data class DatabaseEngNames(
    @PrimaryKey val engNameId: String,
    var engNameOwnerId: String,
    @ColumnInfo(name = "eng_name") var engName: String
)

@Entity(tableName = "texture_images")
data class DatabaseTextureImages(
    @PrimaryKey val databaseTextureImageId: String,
    var textureImageOwnerId: String,
    var nameTextureImage: String,
    var sizeTextureImage: Double
)

@Entity(tableName = "images")
data class DatabaseImages(
    @PrimaryKey val databaseImageId: String,
    var imageOwnerId: String,
    var nameImage: String,
    var sizeImage: Double
)

data class DatabaseModel(
    val nameModel: String,
    val sizeModel: Double
)

data class DatabaseIcon(
    val nameIcon: String,
    val sizeIcon: Double
)

fun List<DatabaseAll>.asModelFish(): List<Fish> {
    return map {
        Fish(
            thNames = it.thNames.asListThNameToString(),
            engNames = it.engNames.asListEngNameToString(),
            scienceName = it.databaseFish.scienceName,
            appearance = it.databaseFish.appearance,
            habitat = it.databaseFish.habitat,
            dissemination = it.databaseFish.dissemination,
            editedAt = it.databaseFish.editedAt,
            createdAt = it.databaseFish.createdAt,
            textureImages = it.textureImages.asListTextureImage(),
            images = it.images.asListImage(),
            model = it.databaseFish.model.asToModel(),
            icon = it.databaseFish.icon.asToIcon(),
            number = it.databaseFish.number,
            status = it.databaseFish.status,
            foundFish = it.databaseFish.foundFish,
            stringDateFound = it.databaseFish.stringDateFound,
            stringTimeFound = it.databaseFish.stringTimeFound
        )
    }
}

fun List<DatabaseThNames>.asListThNameToString(): List<String> {
    return map {
        it.thName
    }
}

fun List<DatabaseEngNames>.asListEngNameToString(): List<String> {
    return map {
        it.engName
    }
}

fun List<DatabaseTextureImages>.asListTextureImage(): List<TextureImage> {
    return map {
        TextureImage(
            name = it.nameTextureImage,
            size = it.sizeTextureImage
        )
    }
}

fun List<DatabaseImages>.asListImage(): List<Image> {
    return map {
        Image(
            name = it.nameImage,
            size = it.sizeImage
        )
    }
}

fun DatabaseModel.asToModel(): Model {
    return Model(nameModel, sizeModel)
}

fun DatabaseIcon.asToIcon(): Icon {
    return Icon(nameIcon, sizeIcon)
}