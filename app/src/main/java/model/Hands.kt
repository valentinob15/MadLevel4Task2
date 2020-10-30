package model

import com.example.madlevel4task2.R

enum class Hands {
    ROCK {
        override fun getImage(): Int = R.drawable.rock
        override fun getId(): Int = 0
    },
    PAPER {
        override fun getImage(): Int = R.drawable.paper
        override fun getId(): Int = 1
    },
    SCISSOR {
        override fun getImage(): Int = R.drawable.scissors
        override fun getId(): Int = 2
    };

    abstract fun getImage():Int
    abstract fun getId(): Int

    companion object{

         fun returnImage(id: Int): Hands? {
            for (image in values()) {
                if (image.getId() == id) {
                    return image
                }

            }
             return null
        }
    }


}