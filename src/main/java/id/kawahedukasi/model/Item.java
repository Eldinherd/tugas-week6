package id.kawahedukasi.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "item")
public class Item extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "itemSequence", sequenceName = "item_sequence",  allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "itemSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "name", nullable = false, length = 50)
    public String name;

    @Column(name = "count", nullable = false)
    public Double count;

    @Column(name = "price", nullable = false)
    public Double price;

    @Column(name = "type", nullable = false, length = 30)
    public String type;

    @Column(name = "description", columnDefinition = "text")
    public String description;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, insertable=false)
    public Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    public java.time.Instant updatedAt;
}
