/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.gpx;

/**
 *
 * @author Serge
 */
public class PersonBuilder {

    protected String name= "noname";
    protected Email email= new Email();
    protected Link link=new Link();

    private PersonBuilder() {
    }

    public static PersonBuilder create() {
        return new PersonBuilder();
    }

    public Person build() {
        return new Person(name, email, link);
    }

    public PersonBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder email(Email email) {
        this.email = email;
        return this;
    }

    public PersonBuilder link(Link link) {
        this.link = link;
        return this;
    }

}
