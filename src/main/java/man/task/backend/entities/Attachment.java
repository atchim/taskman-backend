package man.task.backend.entities;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attachments")
@Getter
@Setter
@AllArgsConstructor
public class Attachment {
  @Id
  @GeneratedValue
  private UUID id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "task_id")
  private Task task;

  private String filename;
  private String url;
}
