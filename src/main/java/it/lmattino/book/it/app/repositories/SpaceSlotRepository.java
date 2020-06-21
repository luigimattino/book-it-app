/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app.repositories;

import it.lmattino.book.it.app.models.SpaceSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mattinò
 */
@Repository
public interface SpaceSlotRepository extends CrudRepository<SpaceSlot, Long> {
    public SpaceSlot findByText(String text);
}