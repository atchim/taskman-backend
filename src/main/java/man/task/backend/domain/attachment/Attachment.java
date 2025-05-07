package man.task.backend.domain.attachment;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;
import man.task.backend.domain.task.Task;

@Entity
@Table(name = "attachments")
@Getter
@Setter
@NoArgsConstructor
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
