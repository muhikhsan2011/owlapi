/*
 * This file is part of the OWL API.
 *
 * The contents of this file are subject to the LGPL License, Version 3.0.
 *
 * Copyright (C) 2011, The University of Manchester
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0
 * in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 *
 * Copyright 2011, University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.manchester.cs.owl.owlapi.alternateimpls;

import org.semanticweb.owlapi.model.OWLAxiomVisitor;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import uk.ac.manchester.cs.owl.owlapi.OWLNamedObjectReferenceAdder;
import uk.ac.manchester.cs.owl.owlapi.OWLNamedObjectReferenceRemover;
import uk.ac.manchester.cs.owl.owlapi.OWLOntologyImpl;

/**
 * @author ignazio
 * threadsafe extension
 */
public class LockingOWLOntologyImpl extends OWLOntologyImpl {
    @SuppressWarnings("javadoc")
	public LockingOWLOntologyImpl(OWLOntologyManager manager, OWLOntologyID ontologyID) {
        super(manager, ontologyID);
        this.internals = new LockingOWLOntologyInternals();
    }

    @Override
	protected OWLAxiomVisitor getAxiomVisitor(boolean add) {
		SyncChangeAxiomVisitor toReturn =new SyncChangeAxiomVisitor(internals, add, ((LockingOWLOntologyInternals)internals).getAxiomTypeLock());
		return toReturn;
	}

    @Override
	protected OWLNamedObjectReferenceAdder getReferenceAdder() {
    	return new SyncOWLNamedObjectReferenceAdderImpl(internals,((LockingOWLOntologyInternals)internals).getAxiomTypeLock());
    }

    @Override
	protected OWLNamedObjectReferenceRemover getReferenceRemover() {
    	return new SyncOWLNamedObjectReferenceRemoverImpl(internals, ((LockingOWLOntologyInternals)internals).getAxiomTypeLock());
    }
}
