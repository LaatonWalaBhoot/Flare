package io.pantheon.flare

class Flare {

    class Builder {
        fun addAdapter(block: () -> Unit): Builder {
            block()
            return this
        }

        fun build() {
            generateEventPropertyMappings()
        }
    }

    companion object {


        fun builder(): Builder {
            return Builder()
        }
    }

}