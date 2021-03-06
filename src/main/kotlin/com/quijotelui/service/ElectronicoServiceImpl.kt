package com.quijotelui.service

import com.quijotelui.model.Electronico
import com.quijotelui.repository.IElectronicoDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ElectronicoServiceImpl : IElectronicoService {

    @Autowired
    lateinit var electronicoDao: IElectronicoDao

    override fun findAll(): MutableList<Electronico> {
        return electronicoDao.findAll()
    }

    override fun findByComprobante(codigo: String, numero: String): MutableList<Electronico> {
        return electronicoDao.findByComprobante(codigo, numero)
    }

    override fun saveElectronico(electronico: Electronico) {
        electronicoDao.saveElectronico(electronico)
    }

    override fun findById(id: Long): Electronico {
        return electronicoDao.findById(id)
    }

    override fun updateElectronico(electronico: Electronico) {
        electronicoDao.updateElectronico(electronico)
    }
}