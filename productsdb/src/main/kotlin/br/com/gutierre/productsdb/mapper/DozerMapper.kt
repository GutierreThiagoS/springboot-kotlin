package br.com.gutierre.productsdb.mapper

import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper

object DozerMapper {

    private val mapper: Mapper = DozerBeanMapperBuilder.buildDefault()

    fun <O, D> parseObject(origin: O, destination: Class<D>?): D {
        return mapper.map(origin, destination)
    }

    fun <O, D> parseListObjects(origin: List<O>, destination: Class<D>?): ArrayList<D> {
        val destinationObjects = ArrayList<D>()
        origin.forEach{ o ->
            destinationObjects.add(mapper.map(o, destination))
        }
        return destinationObjects
    }
}