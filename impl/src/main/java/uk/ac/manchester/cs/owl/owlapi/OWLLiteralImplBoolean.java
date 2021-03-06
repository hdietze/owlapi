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
package uk.ac.manchester.cs.owl.owlapi;

import org.semanticweb.owlapi.model.OWLAnnotationValueVisitor;
import org.semanticweb.owlapi.model.OWLAnnotationValueVisitorEx;
import org.semanticweb.owlapi.model.OWLDataVisitor;
import org.semanticweb.owlapi.model.OWLDataVisitorEx;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectVisitor;
import org.semanticweb.owlapi.model.OWLObjectVisitorEx;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

/**
 * @author Matthew Horridge, The University Of Manchester, Bio-Health
 *         Informatics Group, Date: 26-Oct-2006
 */
public class OWLLiteralImplBoolean extends OWLObjectImpl implements OWLLiteral {

    private static final long serialVersionUID = 30406L;
    private final boolean literal;
    private final OWLDatatype datatype;

    /**
     * @param literal
     *        literal value
     */
    public OWLLiteralImplBoolean(boolean literal) {
        super();
        datatype = OWL2DatatypeImpl.getDatatype(OWL2Datatype.XSD_BOOLEAN);
        this.literal = literal;
        hashcode = getHashCode();
    }

    private final int hashcode;

    @Override
    public int hashCode() {
        return hashcode;
    }

    private int getHashCode() {
        int hashCode = 277;
        hashCode = hashCode * 37 + getDatatype().hashCode();
        hashCode = hashCode * 37 + (literal ? 65536 : 0);
        return hashCode;
    }

    @Override
    public String getLiteral() {
        return Boolean.toString(literal);
    }

    @Override
    public boolean isRDFPlainLiteral() {
        return false;
    }

    @Override
    public boolean hasLang() {
        return false;
    }

    @Override
    public boolean isInteger() {
        return false;
    }

    @Override
    public int parseInteger() throws NumberFormatException {
        throw new NumberFormatException(
                "this literal is not an integer but a boolean");
    }

    @Override
    public boolean isBoolean() {
        return true;
    }

    @Override
    public boolean parseBoolean() throws NumberFormatException {
        return literal;
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public double parseDouble() throws NumberFormatException {
        throw new NumberFormatException(
                "this literal is not a double but a boolean");
    }

    @Override
    public boolean isFloat() {
        return false;
    }

    @Override
    public float parseFloat() throws NumberFormatException {
        throw new NumberFormatException(
                "this literal is not a float but a boolean");
    }

    @Override
    public String getLang() {
        return "";
    }

    @Override
    public boolean hasLang(String l) {
        return false;
    }

    @Override
    public OWLDatatype getDatatype() {
        return datatype;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (obj instanceof OWLLiteralImplBoolean) {
                OWLLiteralImplBoolean other = (OWLLiteralImplBoolean) obj;
                return literal == other.literal
                        && datatype.equals(other.getDatatype());
            }
            if (obj instanceof OWLLiteral) {
                return datatype.equals(((OWLLiteral) obj).getDatatype())
                        && getLiteral().equals(((OWLLiteral) obj).getLiteral());
            }
        }
        return false;
    }

    @Override
    public void accept(OWLDataVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <O> O accept(OWLDataVisitorEx<O> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void accept(OWLAnnotationValueVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <O> O accept(OWLAnnotationValueVisitorEx<O> visitor) {
        return visitor.visit(this);
    }

    @Override
    protected int compareObjectOfSameType(OWLObject object) {
        OWLLiteral other = (OWLLiteral) object;
        int diff = getLiteral().compareTo(other.getLiteral());
        if (diff != 0) {
            return diff;
        }
        int compareTo = datatype.compareTo(other.getDatatype());
        if (compareTo != 0) {
            return compareTo;
        }
        return Boolean.compare(literal, other.parseBoolean());
    }

    @Override
    public void accept(OWLObjectVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <O> O accept(OWLObjectVisitorEx<O> visitor) {
        return visitor.visit(this);
    }
}
