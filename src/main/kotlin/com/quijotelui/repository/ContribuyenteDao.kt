package com.quijotelui.repository

import com.quijotelui.model.Contribuyente
import org.springframework.data.jpa.repository.JpaRepository

interface ContribuyenteDao : JpaRepository<Contribuyente, Long>{
}