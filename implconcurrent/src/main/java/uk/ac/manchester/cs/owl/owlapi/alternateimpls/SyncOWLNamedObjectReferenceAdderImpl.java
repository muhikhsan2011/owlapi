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

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import uk.ac.manchester.cs.owl.owlapi.Internals;
import uk.ac.manchester.cs.owl.owlapi.OWLNamedObjectReferenceAdder;
import uk.ac.manchester.cs.owl.owlapi.OWLNamedObjectReferenceAdderImpl;
/**
 * @author ignazio
 * threadsafe implementation
 */
public class SyncOWLNamedObjectReferenceAdderImpl implements OWLNamedObjectReferenceAdder{
	private final OWLNamedObjectReferenceAdderImpl delegate;

        private final ReadWriteLock lock;

        @SuppressWarnings("javadoc")
		public SyncOWLNamedObjectReferenceAdderImpl(Internals oi, ReadWriteLock lock) {
        	delegate=new OWLNamedObjectReferenceAdderImpl(oi);
			this.lock=lock;
		}


        public void setAxiom(OWLAxiom axiom) {
            this.delegate.setAxiom(axiom);
        }


        public void visit(OWLClass owlClass) {
        	Lock l=lock.writeLock();
        	l.lock();
        	try {
        		delegate.visit(owlClass);
        	}finally {
        		l.unlock();
        	}

        }


        public void visit(OWLObjectProperty property) {
        	Lock l=lock.writeLock();
        	l.lock();
        	try {
        		delegate.visit(property);
        	}finally {
        		l.unlock();
        	}
        }


        public void visit(OWLDataProperty property) {
        	Lock l=lock.writeLock();
        	l.lock();
        	try {
        		delegate.visit(property);
        	}finally {
        		l.unlock();
        	}
        }


        public void visit(OWLNamedIndividual owlIndividual) {
        	Lock l=lock.writeLock();
        	l.lock();
        	try {
        		delegate.visit(owlIndividual);
        	}finally {
        		l.unlock();
        	}
        }

        public void visit(OWLAnnotationProperty property) {
        	Lock l=lock.writeLock();
        	l.lock();
        	try {
        		delegate.visit(property);
        	}finally {
        		l.unlock();
        	}
        }

        public void visit(OWLDatatype datatype) {
        	Lock l=lock.writeLock();
        	l.lock();
        	try {
        		delegate.visit(datatype);
        	}finally {
        		l.unlock();
        	}
        }
    }